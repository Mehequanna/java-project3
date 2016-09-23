import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private String name;
  private String details;
  private int id;

  public Stylist(String name, String details) {
    this.name = name;
    this.details = details;
  }

  public String getName() {
    return name;
  }

  public String getDetails() {
    return details;
  }

  public int getId() {
    return id;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(name, details) VALUES (:name, :details)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("details", this.details)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(String details) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET details = :details WHERE id = :id";
      con.createQuery(sql)
        .addParameter("details", details)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, name, details FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  // public List<Client> getClients() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM clients WHERE stylist_id=:id";
  //     return con.createQuery(sql)
  //       .addParameter("id", this.id)
  //       .executeAndFetch(Client.class);
  //   }
  // }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
             this.getDetails().equals(newStylist.getDetails()) &&
             this.getId() == newStylist.getId();
    }
  }

}
