package co.grandcircus.capstone6;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskListController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TaskRepository taskRepo;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/")
	public String WelcomeOrLogin() {
		if(session.getAttribute("user")!=null) {
			return "welcome";
		}else {
			return "login";
		}
		
	}
	@RequestMapping("/login-submit")
	public String submitLoginForm(Model model,@RequestParam("email") String email,@RequestParam("password") String password) {
		Optional<User> foundUser=userRepo.findByEmailAndPassword(email, password);
		if(foundUser.isPresent()) {
			session.setAttribute("user", foundUser.get());
			return "redirect:/";
		}else {
			model.addAttribute("message","Incorrect email or password.");
			return "login";
		}
	}
	@RequestMapping("/my-tasks")
	public String showTaskList(Model model) {
		User user=(User) session.getAttribute("user");		
		model.addAttribute("tasks",taskRepo.findByUserIdOrderByDueDate(user.getId()));
		return "task-list";
	}
	@RequestMapping("/add-task")
	public String showAddTaskForm() {
		return "add-task";
		
	}
	@PostMapping("/add-task")
	public String submitAddTask(Task task) {
		User user=(User) session.getAttribute("user");		
		user=userRepo.findById(user.getId()).get();
		task.setUser(user);
		taskRepo.save(task);
		return "redirect:/my-tasks";
		
	}
	@RequestMapping("/changeTasktoComplete")
	public String markComplete(Model model,@RequestParam("id") Long id) {
		Task task=(Task)session.getAttribute("task");
		task=taskRepo.findById(id).get();
		if(task.isComplete()==false) {
			task.setComplete(true);			
		}
		taskRepo.save(task);
		return "redirect:/my-tasks";		
				
	}
	@RequestMapping("/removeATask")
	public String removeATask(Model model,@RequestParam("id") Long id) {
		taskRepo.deleteById(id);
		return "redirect:/my-tasks";		
				
	}
	/*@RequestMapping("/searchBy")
	public String listByName(Model model, @RequestParam(required=false) String description,@RequestParam(required=false) LocalDate dueDate) {
		List<Task> tasks=taskRepo.findAllByDescriptionContainsIgnoreCaseAndDueDate(description,Date.valueOf(dueDate));
		model.addAttribute("tasks", tasks);
		return "redirect:/my-tasks";
	}*/
	
	

}
