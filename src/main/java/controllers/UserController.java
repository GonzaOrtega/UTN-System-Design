package controllers;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import domain.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UserController {
	public ModelAndView showProfile(Request req, Response res) {
		Map<String,Object> viewModel = new HashMap<String, Object>();
		String username = req.cookie("nombreUsuario");

		return new ModelAndView(viewModel,"perfil.hbs");
	}
}
