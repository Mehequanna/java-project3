import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("StylistClass", Stylist.class);
      model.put("clients", Client.all());
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String details = request.queryParams("details");
      Stylist stylist = new Stylist(name, details);
      stylist.save();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String details = request.queryParams("details");
      int stylist_id = Integer.parseInt(request.queryParams("stylist_id"));
      Client client = new Client(name, details, stylist_id);
      client.save();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
