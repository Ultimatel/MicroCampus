package org.smile.microcampus.Utils;
import android.support.v4.app.Fragment;

import org.smile.microcampus.Fragments.ConsultFragment;
import org.smile.microcampus.Fragments.LearningFragment;
import org.smile.microcampus.Fragments.LibraryFragment;
import org.smile.microcampus.Fragments.PartTime_Job_Fragment;
import org.smile.microcampus.Fragments.School_in_out_Fragment;
import org.smile.microcampus.Fragments.StudyGuide_Fragment;
import org.smile.microcampus.Fragments.Volunteer_Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ben on 2015/10/16.
 * 创建Fragment工厂
 */
public class FragmentsFactory {
    private static Map<Integer,Fragment> mFragments=new HashMap<Integer,Fragment>();
    public static Fragment creatFragments(int position){
        Fragment fragment;
        fragment=mFragments.get(position);
        if(fragment==null){
            if(position==0){
              fragment=new School_in_out_Fragment();
            }
            else if(position==1){
              fragment=new PartTime_Job_Fragment();
            }
            else if(position==2){
               fragment=new Volunteer_Fragment();
            }
            else if(position==3){
                fragment=new StudyGuide_Fragment();
            }
            else if(position==4){
                fragment=new ConsultFragment();
            }
            else if (position==5){
                fragment=new LibraryFragment();
            }
        }
        if(fragment!=null){
            mFragments.put(position,fragment);
        }
        return fragment;
    }
}
