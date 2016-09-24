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

    post("/stylist/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int stylist_id = Integer.parseInt(request.queryParams("stylist_id"));
      Stylist stylist = Stylist.find(stylist_id);
      stylist.delete();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int client_id = Integer.parseInt(request.queryParams("client_id"));
      Client client = Client.find(client_id);
      client.delete();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:stylist_id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int stylist_id = Integer.parseInt(request.params(":stylist_id"));
      Stylist stylist = Stylist.find(stylist_id);
      model.put("stylist", stylist);
      model.put("template", "templates/stylist-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String details = request.queryParams("details");
      int stylist_id = Integer.parseInt(request.queryParams("stylist_id"));
      Stylist stylist = Stylist.find(stylist_id);
      stylist.update(details);
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/:client_id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int client_id = Integer.parseInt(request.params(":client_id"));
      Client client = Client.find(client_id);
      model.put("stylists", Stylist.all());
      model.put("client", client);
      model.put("template", "templates/client-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String details = request.queryParams("details");
      int stylist_id = Integer.parseInt(request.queryParams("stylist_id"));
      int client_id = Integer.parseInt(request.queryParams("client_id"));
      Client client = Client.find(client_id);
      client.update(details, stylist_id);
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:stylist_id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int stylist_id = Integer.parseInt(request.params(":stylist_id"));
      Stylist stylist = Stylist.find(stylist_id);
      model.put("stylist", stylist);
      model.put("clients", Client.findByStylist(stylist_id));
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
