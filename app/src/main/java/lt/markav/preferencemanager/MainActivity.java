package lt.markav.preferencemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LanguagePreferencesManager languagePreferencesManager = new LanguagePreferencesManager();
        StatePreferencesManager statePreferencesManager = new StatePreferencesManager();
        UserPreferencesManager userPreferencesManager = new UserPreferencesManager();

    }
}
