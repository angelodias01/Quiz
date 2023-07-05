package quiz.app.project.dias.dias.MainMenuUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import quiz.app.project.dias.dias.QuizDatabase.ThemeDB.Theme;
import quiz.app.project.dias.dias.R;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MainPageViewHolder>{
    List<Theme> themeList;

    public MainPageAdapter(List<Theme> themeList) {
        this.themeList = themeList;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public MainPageAdapter.MainPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a view object based on the layout created (chat_item.xml)
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.themes_item, parent, false);
        // Create and return an object of the ChatViewHolder type
        return new MainPageViewHolder(rootView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull MainPageAdapter.MainPageViewHolder holder, int position) {
        Theme theme = themeList.get(position);
        if (getItemViewType(position) == 0){
            holder.lblTheme.setText(theme.getThemeName());
        }
    }

    @Override
    public int getItemCount() {
        if (themeList == null || themeList.isEmpty()) {
            return 0;
        }
        return themeList.size();
    }

    public class MainPageViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private View rootView;
        private TextView lblTheme;

        private RecyclerView recyclerViewHome;

        public MainPageViewHolder(@NonNull View rootView, Context context) {
            super(rootView);
            this.context = context;
            this.rootView = rootView;
            this.lblTheme = this.rootView.findViewById(R.id.lblTheme);
        }
    }

    public void refreshList(List<Theme> newThemeList) {
        Collections.sort(newThemeList, new Comparator<Theme>() {
            @Override
            public int compare(Theme theme1, Theme theme2) {
                return theme1.getThemeName().compareToIgnoreCase(theme2.getThemeName());
            }
        });

        this.themeList.clear();
        this.themeList.addAll(newThemeList);
        notifyDataSetChanged();
    }
}