package fee_software;

public class FinancialManager {
    private DataHandler dataHandler = new DataHandler();

    public int authenticateUser(String username, String userPass) {
        return dataHandler.verifyUserCredentials(username, userPass);
    }

    public boolean registerStudent(Learner learner, int managerId) {
        return dataHandler.insertLearnerRecord(learner, managerId);
    }

    public void displayAllStudents() {
        dataHandler.retrieveAllLearners();
    }

    public void modifyStudent(int studentId, String updatedName, String updatedEmail, String updatedProgram, double updatedFee, double amountPaid, double balanceDue, String updatedAddress, String updatedContact) {
        dataHandler.updateLearnerDetails(studentId, updatedName, updatedEmail, updatedProgram, updatedFee, amountPaid, balanceDue, updatedAddress, updatedContact);
    }

    public void removeStudent(int studentId) {
        dataHandler.deleteLearnerRecord(studentId);
    }

    public void listPendingPayments() {
        dataHandler.fetchOutstandingPayments();
    }
}
