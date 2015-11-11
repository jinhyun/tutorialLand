package tutorialLand.meongmoongCafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EpisodeController {
    @RequestMapping(value = "/episodes", method = RequestMethod.GET)
    public String viewEpisodeList() {
        return "/meongmoongCafe/episodeList";
    }
}
