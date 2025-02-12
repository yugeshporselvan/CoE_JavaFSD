package FinanceManagement;

import java.util.ArrayList;
import java.util.List;

public class AdminHandler {
    private DataAccess adminDB = new DataAccess();

    public boolean authenticateUser(String username, String passkey) {
        return adminDB.verifyLogin(username, passkey);
    }

    public boolean registerUser(String username, String passkey) {
        return adminDB.createUser(username, passkey);
    }

    public boolean addFinancialOfficer(FinancialOfficer officer) {
        return adminDB.insertOfficer(officer);
    }

    public void displayOfficers() {
        adminDB.listOfficers();
    }

    public void modifyOfficer(int officerId, String updatedName, String updatedEmail, String updatedPhone, String updatedPasskey) {
        adminDB.updateOfficer(officerId, updatedName, updatedEmail, updatedPhone, updatedPasskey);
    }

    public void removeOfficer(int officerId) {
        adminDB.deleteOfficer(officerId);
    }
}
