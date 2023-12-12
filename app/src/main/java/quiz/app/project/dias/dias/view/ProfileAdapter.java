/**
 * ProfileAdapter.java
 * Adapter class for the RecyclerView in the profile displaying achievements.
 */

package quiz.app.project.dias.dias.view;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import quiz.app.project.dias.dias.model.achievementsuser.AchievementUser;
import quiz.app.project.dias.dias.model.achievements.Achievements;
import quiz.app.project.dias.dias.R;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<AchievementUser> achievementsUserList;
    private List<Achievements> achievementsList;

    /**
     * Constructor for initializing the adapter with data.
     * @param achievementsUserList List of user-specific achievements.
     * @param achievementsList List of all achievements.
     */
    public ProfileAdapter(List<AchievementUser> achievementsUserList, List<Achievements> achievementsList) {
        // Order and initialize achievementsList; if null, create an empty list.
        this.achievementsUserList = achievementsUserList;
        this.achievementsList = achievementsList != null ? orderAchievementsList(achievementsUserList, achievementsList) : new ArrayList<>();
    }

    /**
     * Inflates the layout for individual items in the RecyclerView.
     * @param parent The parent view group.
     * @param viewType The type of view.
     * @return A new ProfileViewHolder instance.
     */
    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_item, parent, false);
        return new ProfileViewHolder(itemView);
    }

    /**
     * Binds data to the views inside the RecyclerView item.
     * @param holder The ProfileViewHolder instance.
     * @param position The position of the item within the RecyclerView.
     */
    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Achievements achievements = achievementsList.get(position);
        AchievementUser achievementUser = getAchievementUserById(achievements.getAchievementId());
        holder.bind(achievements, achievementUser);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of achievements.
     */
    @Override
    public int getItemCount() {
        return achievementsList.size();
    }

    /**
     * ViewHolder class for holding references to views in each RecyclerView item.
     */
    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        TextView lblAchievement;
        TextView lblDescription;
        TextView lblAchievementDate;
        TextView lblAchievementShow;
        ConstraintLayout constraintLayout;

        /**
         * Constructor to initialize views.
         * @param itemView The view for each item in the RecyclerView.
         */
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            lblAchievement = itemView.findViewById(R.id.lblAchievement);
            lblDescription = itemView.findViewById(R.id.lblDescription);
            lblAchievementDate = itemView.findViewById(R.id.lblAchievementDate);
            constraintLayout = itemView.findViewById(R.id.ConstraintLayout);
            lblAchievementShow = itemView.findViewById(R.id.lblAchievementShow);
        }

        /**
         * Binds data to views in the ViewHolder.
         * @param achievements The Achievement object.
         * @param achievementUser The AchievementUser object.
         */
        public void bind(Achievements achievements, AchievementUser achievementUser) {
            lblAchievement.setText(achievements.getAchievementName());
            lblDescription.setText(achievements.getDescription());

            if (achievementUser != null) {
                // Set background color for achieved achievements.
                constraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D3E9DD")));

                // Format and display achievement date.
                long dateEarned = achievementUser.getDateEarned();
                Date date = new Date(dateEarned);
                SimpleDateFormat sdf = new SimpleDateFormat(" | dd/MM/yyyy | HH:mm |");
                String formattedDate = sdf.format(date);
                lblAchievementDate.setText(formattedDate);
                lblAchievementDate.setVisibility(View.VISIBLE);
                lblAchievementShow.setVisibility(View.VISIBLE);
            } else {
                // Set background color for unachieved achievements.
                constraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f3e8ff")));
                lblAchievementShow.setVisibility(View.GONE);
                lblAchievementDate.setVisibility(View.GONE);
            }
        }
    }

    // Helper method to get AchievementUser by achievementId
    private AchievementUser getAchievementUserById(int achievementId) {
        for (AchievementUser achievementUser : achievementsUserList) {
            if (achievementUser.getAchievementId() == achievementId) {
                return achievementUser;
            }
        }
        return null;
    }

    // Order the achievements list with user achievements first
    private List<Achievements> orderAchievementsList(List<AchievementUser> achievementsUserList, List<Achievements> achievementsList) {
        List<Achievements> orderedList = new ArrayList<>();
        List<Achievements> userAchievements = new ArrayList<>();
        List<Achievements> otherAchievements = new ArrayList<>();

        // Separate user achievements and other achievements
        for (Achievements achievements : achievementsList) {
            boolean hasAchievement = checkUserHasAchievement(achievements.getAchievementId());
            if (hasAchievement) {
                userAchievements.add(achievements);
            } else {
                otherAchievements.add(achievements);
            }
        }

        // Add user achievements first
        orderedList.addAll(userAchievements);

        // Add other achievements after user achievements
        orderedList.addAll(otherAchievements);

        return orderedList;
    }

    // Helper method to get date earned for a specific achievement
    private long getDateEarnedForAchievement(List<AchievementUser> achievementUsers, int achievementId) {
        for (AchievementUser achievementUser : achievementUsers) {
            if (achievementUser.getAchievementId() == achievementId) {
                return achievementUser.getDateEarned();
            }
        }
        return 0L;
    }

    // Helper method to check if the user has a specific achievement
    private boolean checkUserHasAchievement(int achievementId) {
        for (AchievementUser achievementUser : achievementsUserList) {
            if (achievementUser.getAchievementId() == achievementId) {
                return true;
            }
        }
        return false;
    }

    // Method to get the ordered list of achievements
    public List<Achievements> getOrderedAchievementsList() {
        return orderAchievementsList(achievementsUserList, achievementsList);
    }
}