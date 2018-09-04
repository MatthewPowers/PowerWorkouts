package com.PowerWorkouts.powerworkouts.controlers;

import com.PowerWorkouts.powerworkouts.models.*;
import com.PowerWorkouts.powerworkouts.models.Data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("PowerWorkouts")
public class PowerWorkoutControler
{

    @Autowired
    private ExercisesDao exercisesDao;

    @Autowired
    private WorkoutsDao workoutsDao;

    @Autowired
    private WorkoutInfoDao workoutInfoDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private userSessionDao UserSessionDao;

    static int IPWorkoutId = 0;

    static String registrationNameHolder = "";
    static String registrationError = "";
    static String registrationPasswordError = "";
    static String createWorkoutNameError = "";
    static String createWorkoutDescriptionError = "";
    static String createWorkoutNameHolder = "";
    static String  editWorkoutSetsError = "";
    static String  editWorkoutRepsError = "";
    static String loginError = "";
    static String logedInUser = "";
    static String userLogedIn = "";
    static String exerciseNameError = "";
    static String exerciseDescriptionError = "";
    static String exerciseNameHolder = "";

    static boolean ifSessionIsValid = false;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");



    //.............. Home Page ..........................................
    //...................................................................

    @RequestMapping(value="")
    public String index(Model model)
    {
        model.addAttribute("userName", userLogedIn);
        model.addAttribute("title", "Power Workouts");

        return "PowerWorkouts/index";
    }

    //.......................................................
    //.......................................................



    //..........Login and register ....................................
    //.................................................................

    //Controller for Creating a Registration/Login template
    @RequestMapping(value="register", method = RequestMethod.GET)
    public String registerForm(Model model)
    {
        model.addAttribute("User", usersDao.findAll());
        model.addAttribute("title", "Registration");
        model.addAttribute("nameHolder", registrationNameHolder);
        model.addAttribute("error", registrationError);
        model.addAttribute("errorPass", registrationPasswordError);

        return "PowerWorkouts/register";
    }



    @RequestMapping(value="register", method = RequestMethod.POST)
    public String registerForm(@RequestParam String userName, @RequestParam String password, @RequestParam String confirmPassword)
    {
        Boolean nameHasMatch = false;
        registrationNameHolder = userName;
        registrationError = "";
        registrationPasswordError = "";

        if(userName.matches(""))
        {
            registrationError = "Username cannot be empty";
            return "redirect:/PowerWorkouts/register";
        }

        if(password.matches(""))
        {
            registrationPasswordError = "password cannot be empty";
            return "redirect:/PowerWorkouts/register";
        }

        Iterator<Users> it = usersDao.findByuserName(userName).iterator();
        while ( it.hasNext() ) {
            Users u = it.next();

            if(u.getUserName().matches(userName))
            {
                System.out.println("Match Found");
                System.out.println("u name " + u.getUserName() + " userName " + userName);
                nameHasMatch = true;
            }
        }

        if(password.matches(confirmPassword))
        {
            registrationPasswordError = "";
            //passwordMatches = true;
        }
        else
        {
            registrationPasswordError = "Passwords do not match";
            //passwordMatches = false;
            return "redirect:/PowerWorkouts/register";
        }

        if(nameHasMatch == true)
        {
            registrationError = "Username is already taken";
            return "redirect:/PowerWorkouts/register";
        }

        if(nameHasMatch == false)
        {
            registrationError = "";
            registrationPasswordError = "";
            registrationNameHolder = "";

            int HP = password.hashCode();
            String toString = Integer.toString(HP);

            Users tmpUP = new Users();
            tmpUP.setUserName(userName);
            tmpUP.setUserPassword(toString);
            usersDao.save(tmpUP);

            return "redirect:/PowerWorkouts/login";
        }

        return "redirect:/PowerWorkouts/register";
    }



    @RequestMapping(value="login", method = RequestMethod.GET)
    public String login(Model model, HttpSession session)
    {
        model.addAttribute("title", "Login");
        model.addAttribute("error", loginError);

        return "PowerWorkouts/login";
    }



    @RequestMapping(value="login", method = RequestMethod.POST)
    public String login(@RequestParam String userName, @RequestParam String password)
    {

        if(ifSessionIsValid == true)
        {
            loginError = "User Already loged in";
            return "redirect:/PowerWorkouts/login";
        }

        Boolean nameHasMatch = false;
        Boolean passHasMatch = false;

        //check for username
        Iterator<Users> it = usersDao.findByuserName(userName).iterator();
        while ( it.hasNext() ) {
            Users u = it.next();

            if(u.getUserName().matches(userName))
            {
                nameHasMatch = true;
            }
        }

        //check for Password
        int HP = password.hashCode();
        String toString = Integer.toString(HP);
        Iterator<Users> it2 = usersDao.findByuserPassword(toString).iterator();
        while ( it2.hasNext() ) {
            Users u = it2.next();

            if(u.getUserPassword().matches(toString))
            {
                passHasMatch = true;
            }
        }

        if(nameHasMatch == false || passHasMatch == false)
        {
            loginError = "Username or password does not match";
            return "redirect:/PowerWorkouts/login";
        }

        if(nameHasMatch == true && passHasMatch == true)
        {
            loginError = "";
        }

        logedInUser = userName;

        Date date = new Date();
        Timestamp timestamp = new  Timestamp(date.getTime());

        String UPH = userName + password;
        userSession tmpUS = new userSession();
        tmpUS.setuserNameSession(userName);
        tmpUS.setSessionId(UPH.hashCode());
        tmpUS.setValidSession(true);
        tmpUS.setUserLogTime(timestamp.toString());

        UserSessionDao.save(tmpUS);

        Iterator<userSession> it3 = UserSessionDao.findByuserNameSession(userName) .iterator();
        while ( it3.hasNext() ) {
            userSession u = it3.next();

            if(u.getuserNameSession().matches(userName))
            {
                ifSessionIsValid = true;
                userLogedIn = userName;
            }
        }

        return "redirect:/PowerWorkouts/workouts";
    }

    //.......................................................
    //.......................................................



    //........................Controller to logout.....................................
    //..................................................................................
    @RequestMapping(value="logout")
    public String logout()
    {
        Iterator<userSession> it3 = UserSessionDao.findByuserNameSession(userLogedIn).iterator();
        while ( it3.hasNext() )
        {
            userSession u = it3.next();

            if(u.getuserNameSession().matches(userLogedIn))
            {
                //int usid = u.getId();
                u.setValidSession(false);

                System.out.println("logout: " + u.isValidSession());
                //UserSessionDao.deleteById(usid);
                ifSessionIsValid = false;
                userLogedIn = "";

            }
        }

        loginError = "";

        return "redirect:/PowerWorkouts/login";
    }



    //.........Controller for Exercise List template and adding new exercises...........
    //..................................................................................

    @RequestMapping(value="exerciseList", method = RequestMethod.GET)
    public String exerciseList(Model model)
    {
        if(ifSessionIsValid == false)
        {
            return "redirect:/PowerWorkouts/login";
        }
        model.addAttribute("userName", userLogedIn);
        model.addAttribute("exercises", exercisesDao.findAll());
        model.addAttribute("title", "Workout List");
        model.addAttribute("exerciseNameError", exerciseNameError);
        model.addAttribute("exerciseDescriptionError", exerciseDescriptionError);
        model.addAttribute("exerciseNameHolder", exerciseNameHolder);

        return "/PowerWorkouts/exerciseList";
    }



    @RequestMapping(value="exerciseList", method = RequestMethod.POST)
    public String exerciseListPost(@RequestParam String exerciseName, @RequestParam String description)//@ModelAttribute Exercises newExercise
    {
        Boolean nameHasMatch = false;
        exerciseNameError = "";
        exerciseDescriptionError = "";

        exerciseNameHolder = exerciseName;

        if(exerciseName.matches(""))
        {
            exerciseNameError = "Exercise cant be empty";
            return "redirect:/PowerWorkouts/exerciseList";
        }

        Iterator<Exercises> it = exercisesDao.findByexerciseName(exerciseName).iterator();
        while ( it.hasNext() ) {
            Exercises u = it.next();

            if(u.getExerciseName().matches(exerciseName))
            {
                nameHasMatch = true;
            }
        }

        if(nameHasMatch == true)
        {
            exerciseNameError = "That Exercise already ixsistes";
            return "redirect:/PowerWorkouts/exerciseList";
        }

        if(description.matches(""))
        {
            exerciseDescriptionError = "Exercise needs a description";
            return "redirect:/PowerWorkouts/exerciseList";
        }

        Exercises tmpE = new Exercises();
        tmpE.setExerciseName(exerciseName);
        tmpE.setDescription(description);
        exercisesDao.save(tmpE);

        exerciseNameError = "";
        exerciseDescriptionError = "";
        exerciseNameHolder = "";

        return "redirect:/PowerWorkouts/exerciseList";
    }



    @RequestMapping(value = "removeExercises", method = RequestMethod.GET)
    public String removeExercises(Model model)
    {
        if(ifSessionIsValid == false)
        {
            return "redirect:/PowerWorkouts/login";
        }

        model.addAttribute("userName", userLogedIn);
        model.addAttribute("exercises", exercisesDao.findAll());
        model.addAttribute("title", "Remove Exercise");

        return "PowerWorkouts/removeExercises";
    }

    @RequestMapping(value = "removeExercises", method = RequestMethod.POST)
    public String removeExercises(@RequestParam int[] exerciseIds)
    {
        for(Object exerciseId : exerciseIds)
        {
            int delExerciseId = (int) exerciseId;
            exercisesDao.deleteById(delExerciseId);
        }

        return "redirect:/PowerWorkouts/removeExercises";
    }

    //.......................................................
    //.......................................................



    //.........Controller for Workouts template ........................................
    //..................................................................................

    @RequestMapping(value = "workouts", method = RequestMethod.GET)
    public String workouts(Model model)
    {
        if(ifSessionIsValid == false)
        {
            return "redirect:/PowerWorkouts/login";
        }

        model.addAttribute("userName", userLogedIn);
        IPWorkoutId = 0;
        model.addAttribute("title", "Workouts");
        model.addAttribute("workouts", workoutsDao.findAll());
        model.addAttribute("createWorkoutNameError", createWorkoutNameError);
        model.addAttribute("createWorkoutDescriptionError", createWorkoutDescriptionError);
        model.addAttribute("createWorkoutNameHolder", createWorkoutNameHolder);

        return "PowerWorkouts/workouts";
    }



    @RequestMapping(value = "workouts", method = RequestMethod.POST, params="action=Create Workout")
    public String workouts(@RequestParam String workoutName, @RequestParam String workoutDescription)//, @RequestParam int editWorkoutId)@ModelAttribute Workouts newWorkout
    {

        createWorkoutNameHolder = workoutName;
        if(workoutName == "")
        {
            createWorkoutNameError = "Workout Must have a name";
            return "redirect:/PowerWorkouts/workouts";
        }
        if(workoutDescription == "")
        {
            createWorkoutDescriptionError = "Workout Must have a Description";
            return "redirect:/PowerWorkouts/workouts";
        }

        Workouts tmpWO = new Workouts();
        tmpWO.setWorkoutName(workoutName);
        tmpWO.setWorkoutDescription(workoutDescription);
        tmpWO.setWorkoutUserName(logedInUser);
        workoutsDao.save(tmpWO);

        createWorkoutNameError = "";
        createWorkoutDescriptionError = "";
        createWorkoutNameHolder = "";

        return "redirect:/PowerWorkouts/workouts";
    }

    @RequestMapping(value = "workouts", method = RequestMethod.POST, params="action=Edit")
    public String workouts(@RequestParam(required = true) int workoutIds)
    {
        if(workoutIds < 0)
        {
            System.out.println("no workoutId");
            return "redirect:/PowerWorkouts/workouts";
        }

        System.out.println("workoutIds" + workoutIds);

        if(workoutIds >= 0)
        {
            IPWorkoutId = workoutIds;

            return "redirect:/PowerWorkouts/editWorkout";
        }
        else
        {
            return "redirect:/PowerWorkouts/workouts";
        }

    }

    //.......................................................
    //.......................................................



    @RequestMapping(value = "editWorkout", method = RequestMethod.GET)
    public String editWorkout(Model model)
    {
        if(ifSessionIsValid == false)
        {
            return "redirect:/PowerWorkouts/login";
        }

        model.addAttribute("userName", userLogedIn);
        model.addAttribute("title", "Edit Workout");
        model.addAttribute("workouts", workoutsDao.findById(IPWorkoutId).get());
        model.addAttribute("exercisesList", exercisesDao.findAll());
        model.addAttribute("exercises", getExercisesByWorkoutID(IPWorkoutId));
        model.addAttribute("SetsAndReps", getSetsAndRepsByWorkoutID(IPWorkoutId));
        model.addAttribute("editWorkoutSetsError", editWorkoutSetsError);
        model.addAttribute("editWorkoutRepsError", editWorkoutRepsError);

        return "PowerWorkouts/editWorkout";
    }


    @RequestMapping(value = "editWorkout", method = RequestMethod.POST)
    public String editWorkoutPost(@RequestParam int example, @RequestParam String sets, @RequestParam String reps)
    {

        if(sets == "")
        {
            editWorkoutSetsError = "Must have # of sets";
            return "redirect:/PowerWorkouts/editWorkout";
        }
        if(reps == "")
        {
            editWorkoutRepsError = "Must have # of reps";
            return "redirect:/PowerWorkouts/editWorkout";
        }

        int SetsInt = Integer.parseInt(sets);
        int RepsInt = Integer.parseInt(reps);

        WorkoutInfo tmpWI = new WorkoutInfo();
        tmpWI.setWorkoutId(IPWorkoutId);
        tmpWI.setExerciseId(example);
        tmpWI.setSets(SetsInt);
        tmpWI.setReps(RepsInt);
        tmpWI.setExerciseName(exercisesDao.findById(example).get().getExerciseName());
        workoutInfoDao.save(tmpWI);

        editWorkoutSetsError = "";
        editWorkoutRepsError = "";

        return "redirect:/PowerWorkouts/editWorkout";
    }



    /*--------------------------------------
     * get excercise id by workout id
     * receives workout info id
     * returns excercise
     */
    ArrayList<Exercises> getExercisesByWorkoutID ( int WID )
    {

        Iterable<WorkoutInfo> itw = workoutInfoDao.findAll();
        Iterator<WorkoutInfo> it = itw.iterator();
        WorkoutInfo tmpWI = null;
        ArrayList<Exercises> excs = new ArrayList();

        while ( it.hasNext() )
        {
            tmpWI = it.next();
            if ( tmpWI.getWorkoutId() == WID )
            {
                excs.add( exercisesDao.findById(tmpWI.getExerciseId()).get() );
            }
        }

        return excs;
    }



    ArrayList<WorkoutInfo> getSetsAndRepsByWorkoutID (int WidSR)
    {
        Iterable<WorkoutInfo> itw = workoutInfoDao.findAll();
        Iterator<WorkoutInfo> it = itw.iterator();
        WorkoutInfo tmpWI = null;
        ArrayList<WorkoutInfo> SetsAndReps = new ArrayList();

        while ( it.hasNext() )
        {
            tmpWI = it.next();
            if ( tmpWI.getWorkoutId() == WidSR )
            {
                SetsAndReps.add( workoutInfoDao.findById(tmpWI.getId()).get());
            }
        }

        return SetsAndReps;
    }

// end of page
}
