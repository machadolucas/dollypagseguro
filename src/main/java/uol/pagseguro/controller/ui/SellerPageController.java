package uol.pagseguro.controller.ui;

import org.glassfish.jersey.internal.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uol.pagseguro.entity.ComandaEntity;
import uol.pagseguro.entity.SellerEntity;
import uol.pagseguro.service.ComandaService;
import uol.pagseguro.service.SellerService;
import uol.pagseguro.vo.ComandaVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by machadolucas on 02/11/16.
 */
@Controller
public class SellerPageController {

    @Autowired
    private ComandaService comandaService;

    @Autowired
    private SellerService sellerService;

    @RequestMapping("/")
    public String index(final Model model) {

        final SellerEntity sellerEntity = this.sellerService.findFirst();
        final List<ComandaVO> comandas = this.comandaService.listComandas(sellerEntity.getEmail());

        model.addAttribute("vendedor", sellerEntity);
        model.addAttribute("comandas", comandas);
        model.addAttribute("isHome", "home");

        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/web/comanda/{idComanda}")
    public String detailComanda(final Model model, @PathVariable("idComanda") final String idComanda) {

        final SellerEntity sellerEntity = this.sellerService.findFirst();
        final ComandaEntity comanda = this.comandaService.getComanda(sellerEntity.getEmail(), idComanda);

        model.addAttribute("vendedor", sellerEntity);
        model.addAttribute("comanda", comanda);
        return "detailComanda";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/web/comanda/{idComanda}/closeToPay")
    public String closeToPay(final Model model, @PathVariable("idComanda") final String idComanda, //
                             @RequestParam("file") final MultipartFile file, //
                             @RequestParam("value") final String valueString) throws Exception {

        final SellerEntity sellerEntity = this.sellerService.findFirst();
        final ComandaEntity comanda = this.comandaService.getComanda(sellerEntity.getEmail(), idComanda);

        final String base64Image = Base64.encodeAsString(file.getBytes());
        final BigDecimal valor = new BigDecimal(valueString);
        this.comandaService.closeToPayment(idComanda, sellerEntity.getEmail(), valor, base64Image);

        model.addAttribute("vendedor", sellerEntity);
        model.addAttribute("comanda", comanda);
        return "detailComanda";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/web/comanda/{idComanda}/finalize")
    public String finalize(final Model model, @PathVariable("idComanda") final String idComanda) throws Exception {

        final SellerEntity sellerEntity = this.sellerService.findFirst();
        final ComandaEntity comanda = this.comandaService.getComanda(sellerEntity.getEmail(), idComanda);

        this.comandaService.finalizeComanda(idComanda, sellerEntity.getEmail());

        model.addAttribute("vendedor", sellerEntity);
        model.addAttribute("comanda", comanda);
        return "detailComanda";
    }
}
