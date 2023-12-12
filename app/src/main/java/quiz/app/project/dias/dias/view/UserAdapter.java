/**
 * UserAdapter.java
 * RecyclerView Adapter for handling User items.
 */

package quiz.app.project.dias.dias.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.model.user.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersViewHolder> {
    private List<User> userList;
    private Context context;

    // Constructor to initialize the adapter with a list of users
    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout and create a new ViewHolder
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_login_fragment, parent, false);
        return new UsersViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        // Get the data model based on position
        User current = userList.get(position);

        // Extract relevant information from the current user
        int currentUser = current.getUserId();
        int pos = position;

        // Update the UI elements of the ViewHolder with the extracted information
        // (Note: The actual binding of data to UI elements would depend on your requirements)
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return userList.size();
    }

    // Inner class representing the ViewHolder for user items
    public class UsersViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        EditText tbEmail, tbPassword;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.tbEmail = rootView.findViewById(R.id.tbEmail);
            this.tbPassword = rootView.findViewById(R.id.tbPassword);
        }
    }
}