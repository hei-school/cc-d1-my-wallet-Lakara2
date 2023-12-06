import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
        // Ajoutez un utilisateur par défaut s'il n'y en a pas
        if (users.isEmpty()) {
            createUser("bryan", "lakara");
        }
    }

    public void createUser(String username, String password) {
        users.add(new User(username, password));
        System.out.println("Utilisateur créé avec succès !");
    }

    public boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}