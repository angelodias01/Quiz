package quiz.app.project.dias.dias.QuizDatabase.UserDB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import quiz.app.project.dias.dias.R;

public class UserAdapter {
    private List<User> userList;
    private Context context;

    public UserAdapter(List<User> userList){
        this.userList = userList;
    }

    @NonNull
    //@Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsersViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_login_fragment, parent, false));
    }

    //@Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {

        User current = userList.get(position);

        int currentUser = current.getUserId();

        int pos = position;

    }

    //@Override
    public int getItemCount() {
        return userList.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{
        View rootView;
        EditText tbEmail, tbPassword;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            this.rootView = rootView;
            this.tbEmail = rootView.findViewById(R.id.tbEmail);
            this.tbPassword = rootView.findViewById(R.id.tbPassword);

        }
    }
}
