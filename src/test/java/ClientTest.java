import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client myClient = new Client("Name", "Details", 1);
    assertEquals(true, myClient instanceof Client);
  }

  @Test
  public void Client_instantiatesWithName_String() {
    Client myClient = new Client("Name", "Details", 1);
    assertEquals("Name", myClient.getName());
  }

  @Test
  public void Client_instantiatesWithDetails_String() {
    Client myClient = new Client("Name", "Details", 1);
    assertEquals("Details", myClient.getDetails());
  }

  @Test
  public void Client_instantiatesWithStylistId_int() {
    Client myClient = new Client("Name", "Details", 1);
    assertEquals(1, myClient.getStylistId());
  }

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Name", "Details", 0);
    firstClient.save();
    Client secondClient = new Client("Name2", "Details", 0);
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(firstClient));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

  @Test
  public void clear_emptiesAllClientssFromArrayList_0() {
    Client myClients = new Client("Name", "Details", 1);
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void getId_clientsInstantiateWithAnID_1() {
    Client myClient = new Client("Name", "Details", 1);
    myClient.save();
    assertTrue(myClient.getId() > 0);
  }

  @Test
  public void find_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client("Name", "Details", 1);
    firstClient.save();
    Client secondClient = new Client("Name", "Details", 1);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Client firstClient = new Client("Name", "Details", 1);
    Client secondClient = new Client("Name", "Details", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("Name", "Details", 1);
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist myStylist = new Stylist("StylistName", "StylistDetails");
    myStylist.save();
    Client myClient = new Client("Name", "Details", myStylist.getId());
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertEquals(savedClient.getStylistId(), myStylist.getId());
  }

  @Test
  public void update_updatesClientName_true() {
    Client myClient = new Client("Name", "Details", 1);
    myClient.save();
    myClient.update("UpDetails", 2);
    assertEquals("UpDetails", Client.find(myClient.getId()).getDetails());
  }

  @Test
  public void delete_deletesClient_true() {
    Client myClient = new Client("Name", "Details", 1);
    myClient.save();
    int myClientId = myClient.getId();
    myClient.delete();
    assertEquals(null, Client.find(myClientId));
  }

}
