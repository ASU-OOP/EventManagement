package Views;

import Models.Users.Admin;
import Models.Users.Attendee;
import Models.Users.Organizer;
import Models.Users.User;
import javafx.stage.Stage;

public class DashboardView {
    AdminDashboardView adminDashboardView = new AdminDashboardView();
    AttendeeDashboardView attendeeDashboardView = new AttendeeDashboardView();
    OrganizerDashboardView organizerDashboardView = new OrganizerDashboardView();

    public void dashboardView(User user, Stage stage){
        if (user instanceof Admin){
            adminDashboardView.adminDashboardView(this, (Admin) user, stage);
        } else if (user instanceof Attendee) {
            attendeeDashboardView.attendeeDashboardView(this, (Attendee) user, stage);
        } else if (user instanceof Organizer){
            organizerDashboardView.organizerDashboardView(this, (Organizer) user, stage);
        }
    }
}