package lt.markav.preferencemanager;


import java.util.ArrayList;
import java.util.List;

import lt.markav.preference.Preferences;

@Preferences
public class UserPreferences {

    protected String userName = "";
    protected int age = 22;
    protected long timestamp = 1494572865;
    protected List<Integer> orders = new ArrayList<>();

}
