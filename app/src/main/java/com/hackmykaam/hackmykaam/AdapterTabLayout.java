package com.hackmykaam.hackmykaam;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdapterTabLayout extends FragmentPagerAdapter {

    private final String[] TITLES = { "Home", "Engeering", "Create Project", " Daily Word", "Build Your Websiter",
             };
    public AdapterTabLayout(FragmentManager fm) {
        super(fm);
    }




    private Context myContext;
    int totalTabs;

    public AdapterTabLayout(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
         return TITLES[position];
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentHome homeFragment = new FragmentHome();
                return homeFragment;
            case 1:
                FragmentEngineer engineerFragment = new FragmentEngineer();
                return engineerFragment;
            case 2:
                FragmentCreateProject createProjectFragment = new FragmentCreateProject();
                return createProjectFragment;
            case 3:
                FragmentDailyWord dailyWord = new FragmentDailyWord();
                return dailyWord;
            case 4:
                FragmentProgrammingConcept programmingConcept = new FragmentProgrammingConcept();
                return programmingConcept;
            case 5:
                FragmentBuildYourWebsite buildYourWebsite = new FragmentBuildYourWebsite();
                return buildYourWebsite;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return TITLES.length;
    }
}
