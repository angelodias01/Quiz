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

    public ProfileAdapter(List<AchievementUser> achievementsUserList, List<Achievements> achievementsList) {
        this.achievementsUserList = achievementsUserList;
        this.achievementsList = achievementsList != null ? orderAchievementsList(achievementsUserList, achievementsList) : new ArrayList<>();
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_item, parent, false);
        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Achievements achievements = achievementsList.get(position);
        AchievementUser achievementUser = getAchievementUserById(achievements.getAchievementId());
        holder.bind(achievements, achievementUser);
    }

    @Override
    public int getItemCount() {
        return achievementsList.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        TextView lblAchievement;
        TextView lblDescription;
        TextView lblAchievementDate;
        TextView lblAchievementShow;
        ConstraintLayout constraintLayout;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            lblAchievement = itemView.findViewById(R.id.lblAchievement);
            lblDescription = itemView.findViewById(R.id.lblDescription);
            lblAchievementDate = itemView.findViewById(R.id.lblAchievementDate);
            constraintLayout = itemView.findViewById(R.id.ConstraintLayout);
            lblAchievementShow = itemView.findViewById(R.id.lblAchievementShow);
        }

        public void bind(Achievements achievements, AchievementUser achievementUser) {
            lblAchievement.setText(achievements.getAchievementName());
            lblDescription.setText(achievements.getDescription());

            if (achievementUser != null) {
                constraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D3E9DD")));

                long dateEarned = achievementUser.getDateEarned();
                Date date = new Date(dateEarned);
                SimpleDateFormat sdf = new SimpleDateFormat(" | dd/MM/yyyy | HH:mm |");
                String formattedDate = sdf.format(date);
                lblAchievementDate.setText(formattedDate);
                lblAchievementDate.setVisibility(View.VISIBLE);
                lblAchievementShow.setVisibility(View.VISIBLE);
            } else {
                constraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f3e8ff")));
                lblAchievementShow.setVisibility(View.GONE);
                lblAchievementDate.setVisibility(View.GONE);
            }
        }
    }

    private AchievementUser getAchievementUserById(int achievementId) {
        for (AchievementUser achievementUser : achievementsUserList) {
            if (achievementUser.getAchievementId() == achievementId) {
                return achievementUser;
            }
        }
        return null;
    }

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

    private long getDateEarnedForAchievement(List<AchievementUser> achievementUsers, int achievementId) {
        for (AchievementUser achievementUser : achievementUsers) {
            if (achievementUser.getAchievementId() == achievementId) {
                return achievementUser.getDateEarned();
            }
        }
        return 0L;
    }

    private boolean checkUserHasAchievement(int achievementId) {
        for (AchievementUser achievementUser : achievementsUserList) {
            if (achievementUser.getAchievementId() == achievementId) {
                return true;
            }
        }
        return false;
    }
    public List<Achievements> getOrderedAchievementsList() {
        return orderAchievementsList(achievementsUserList, achievementsList);
    }
}
