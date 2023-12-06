const User = require('./User');

class UserManager {
    constructor() {
        this.users = [];
        if (this.users.length === 0) {
            this.createUser("bryan", "lakara");
        }
    }

    createUser(username, password) {
        this.users.push(new User(username, password));
        console.log("Utilisateur créé avec succès !");
    }

    authenticate(username, password) {
        for (let user of this.users) {
            if (user.username === username && user.password === password) {
                return true;
            }
        }
        return false;
    }
}

module.exports = UserManager;