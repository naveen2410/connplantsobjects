/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package connplantsv1.connplantsv1;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

	
	
	@GetMapping
    public String index() {
      return "redirect:/login";
    }
	
	@GetMapping("/login")
	  public String loginForm(Model model) {
	    model.addAttribute("login", new Login());
	    return "login";
	  }
	
	  @PostMapping("/login")
	  public String loginSubmit(@ModelAttribute Login login) {
		  
		  //String StrURL = "http://"+login.getIp()+":"+login.getPort()+"/XMII/Illuminator?service=scheduler&mode=List&content-type=text/xml&IllumLoginName=" + login.getUser() + "&IllumLoginPassword=" + login.getPassword();
		  String StrURL = "http://"+login.getIp()+":"+login.getPort()+"/XMII/Illuminator?IsTesting=T&QueryTemplate=Default/Som/OCP_ConnPlants/ProductionOrder/SQL_GetShopOrderDetails&Content-Type=text/xml&IllumLoginName="+login.getUser()+"&IllumLoginPassword="+login.getPassword();
		  try {
			login.setStringURL(new GetMIIResponse().executeGETService(StrURL));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  return "welcome";
	  }
	  
	  @GetMapping("/display")
	    public String getMIISchJob(Model model) {
	      
		  List<MIISchJob> listMIISchJobs = new ArrayList<MIISchJob>();
		  
		  //DisplayMIISchJob displayMIIjob = new DisplayMIISchJob();
		  ShowMIISchJobList displayMIIjob = new ShowMIISchJobList();
		  try {
			displayMIIjob.displayMIISchJobs(listMIISchJobs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  model.addAttribute("miischjobs", listMIISchJobs);
	        
	      return "display";
	    }
	  
	  @PostConstruct
	    public void init() throws Exception {
		  
		  List<MIISchJob> listMIISchJobs = new ArrayList<MIISchJob>();
		  
		//DisplayMIISchJob displayMIIjob = new DisplayMIISchJob();
		  ShowMIISchJobList displayMIIjob = new ShowMIISchJobList();
		  displayMIIjob.displayMIISchJobs(listMIISchJobs);
		  
	  }
	  

	   
}
