package quiz.app.project.dias.dias.model.theme;

import java.util.List;

public class ThemeResponse {
    public List<Theme> data;

    public ThemeResponse(List<Theme> data) {
        this.data = data;
    }

    public List<Theme> getData() {
        return data;
    }
}
