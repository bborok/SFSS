package com.zeta.Controllers;

import com.zeta.Data.Statistics.StatisticsDao;
import com.zeta.Data.Statistics.StatisticsData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
public class StatisticsController {

    @GetMapping("/statistics/info_lf")
    public String statistics_info_lf(Model m) {
        return "statistics_info_lf";
    }

    @RequestMapping(value = "/statistics/info_lf/data/get", produces="application/json", method = RequestMethod.GET)
    @ResponseBody
    public String get_statistics_info_lf_data(String campus) {
        if (campus == null)
            campus = "Burnaby";
        StatisticsData sd = new StatisticsDao();
        String[] strs = {"directions", "lost & found", "payments", "phone services", "key services", "other inquiries"};
        int[][] array = sd.getDataForInfoLF(campus, strs);
        JSONObject ret = new JSONObject();
        JSONArray title_json = new JSONArray();
        Date currDate = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(currDate);
        int currYear = ca.get(Calendar.YEAR);
        String[] title_strs = {String.valueOf(currYear), "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        for (String str : title_strs) {
            title_json.add(str);
        }
        ArrayList<JSONArray> vals = new ArrayList<JSONArray>();
        for (int j = 0; j < 6; j++) {
            JSONArray tmp = new JSONArray();
            for (int i = 0; i < 12; i++) {
                tmp.add(array[j][i]);
            }
            vals.add(tmp);
        }
        ret.put("title", title_json);
        ret.put(strs[0], vals.get(0));
        ret.put(strs[1], vals.get(1));
        ret.put(strs[2], vals.get(2));
        ret.put(strs[3], vals.get(3));
        ret.put(strs[4], vals.get(4));
        ret.put(strs[5], vals.get(5));
        return ret.toString();
    }

    @RequestMapping(value = "/statistics/info_lf/data/post", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String post_statistics_info_lf_data(String campus, String data) {
        if (campus == null)
            campus = "Burnaby";
        StatisticsData sd = new StatisticsDao();
        JSONArray jsonArray = JSONArray.fromObject(data);
        System.out.println(jsonArray.toString());
        String[] titles = {"directions", "lost & found", "payments", "phone services", "key services", "other inquiries"};
        for (int i = 0; i < 6; i++) {
            int[] tmp_arr = new int[12];
            for (int j = 0; j < 12; j++) {
                System.out.println(jsonArray.getInt(i*12 + j));
                tmp_arr[j] = jsonArray.getInt(i * 12 + j);
            }
            sd.updateDataForInfoLF(campus, titles[i], tmp_arr);
        }
        JSONObject retObject = new JSONObject();
        retObject.put("result", "success");
        retObject.put("message", "success");
        return retObject.toString();
    }

    @GetMapping("/statistics/public_contact")
    public String statistics_public_contact(Model m) {
        return "statistics_public_contact";
    }

    @RequestMapping(value="/statistics/public_contact/data", produces="application/json", method = RequestMethod.GET)
    @ResponseBody
    public String statistics_public_contact_data(String campus) {
        if (campus == null)
            campus = "Burnaby";
        StatisticsData sd = new StatisticsDao();
        Date currDate = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(currDate);
        int currYear = ca.get(Calendar.YEAR);
        String[] strs = {"Smoke Prevention", "Theft Prevention", "Public Contact", "Safe Walk", "Hazard/Service Request", "Assist Security"};
        int[][] array = sd.getDataForPublicContact(campus, strs);
        JSONObject ret = new JSONObject();
        JSONArray title_json = new JSONArray();
        String[] title_strs = {String.valueOf(currYear), "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        for (String str : title_strs) {
            title_json.add(str);
        }

        ArrayList<JSONArray> vals = new ArrayList<JSONArray>();
        for (int j = 0; j < 6; j++) {
            JSONArray tmp = new JSONArray();
            for (int i = 0; i < 12; i++) {
                tmp.add(array[j][i]);
            }
            vals.add(tmp);
        }
        ret.put("title", title_json);
        ret.put(strs[0], vals.get(0));
        ret.put(strs[1], vals.get(1));
        ret.put(strs[2], vals.get(2));
        ret.put(strs[3], vals.get(3));
        ret.put(strs[4], vals.get(4));
        ret.put(strs[5], vals.get(5));
        return ret.toString();
    }
}
