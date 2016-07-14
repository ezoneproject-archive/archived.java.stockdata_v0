package stidx.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import stidx.util.DateUtil;

public class Upload extends AbstractController {
    Logger log = LogManager.getLogger(Upload.class);

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView mav = new ModelAndView("upload");
        mav.addObject("gsdate", DateUtil.getCurrentFormat("yyyy-MM-dd"));
        mav.addObject("menu_upload", "active");
        return mav;
    }

}
