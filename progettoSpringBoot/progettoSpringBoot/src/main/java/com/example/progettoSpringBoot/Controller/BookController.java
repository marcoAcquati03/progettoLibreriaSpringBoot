package com.example.progettoSpringBoot.Controller;

import com.example.progettoSpringBoot.Model.Libro;
import com.example.progettoSpringBoot.Dao.LibroDao;
import com.example.progettoSpringBoot.Dao.UserDao;
import com.example.progettoSpringBoot.Model.User;
import com.example.progettoSpringBoot.Dao.UserLibroDao;
import com.example.progettoSpringBoot.Model.UserLibro;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class BookController {

        @Autowired
        private LibroDao libroRepository;
        @Autowired
        private UserDao userRepository;

        @Autowired
        private UserLibroDao userLibroRepository;

        @GetMapping(value = "dashboard")
        public String dashboard(HttpSession session){
                if (session.getAttribute("loggedUser") == null) return "redirect:/login";
                return "dashboard";
        }


        @GetMapping(value = "/aggiungiLibro")
        public String addBookPage(Libro libro, HttpSession session) {;
                if (session.getAttribute("loggedUser") == null) return "redirect:/login";

                return "aggiungiLibro";
        }

        @PostMapping(value = "/aggiungiLibroAction")
        public String aggiungiLibroAction(@Valid Libro libro, BindingResult bindingResult) {
                if (bindingResult.hasErrors()) return "aggiungiLibro";

                for(Libro l : libroRepository.findAll()){
                        if(Objects.equals(l.getTitolo().toLowerCase(), libro.getTitolo().toLowerCase()) &&
                                l.getAutore().toLowerCase().equals(libro.getAutore().toLowerCase()))
                                return "redirect:/infoLibro";
                }

                libroRepository.save(libro);

                return "redirect:/dashboard";
        }


        @GetMapping(value = "/infoLibro")
        public String infoLibro(Model model,HttpSession session) {
                if (session.getAttribute("loggedUser") == null) return "redirect:/login";

                model.addAttribute("libri", libroRepository.findAll());
                return "infoLibro";
        }

        @GetMapping(value = "/iTuoiLibri")
        public String iTuoiLibri(HttpSession session, Model model){
                if (session.getAttribute("loggedUser") == null) return "redirect:/login";

                User loggedUser = (User) session.getAttribute("loggedUser");
                Optional<User> u = userRepository.findById(loggedUser.getId());

                List<Libro> userBooks = new ArrayList<>();
                for (UserLibro l :  u.get().getUserLibri()){
                        userBooks.add(l.getLibro());
                }

                model.addAttribute("libri", userBooks);

                return "iTuoiLibri";
        }

        @GetMapping(value="/aggiungiLibroPreferito")
        public String aggiungiLibroPreferito(HttpSession session, @RequestParam("libro_preferito_id") long id){
                User loggedUser = (User) session.getAttribute("loggedUser");

                for (UserLibro userBook : loggedUser.getUserLibri()){
                        if(userBook.getLibro().getId() == id) return "redirect:/iTuoiLibri";
                }

                Libro libro = libroRepository.findById(id);
                UserLibro userLibro = new UserLibro(loggedUser,libro);
                userLibroRepository.save(userLibro);
                return "redirect:/iTuoiLibri";
        }

        @GetMapping(value = "/rimouviDaiPreferiti")
        public String rimouviDaiPreferiti(HttpSession session, @RequestParam("libro_preferito_id") long id){
                User loggedUser = (User) session.getAttribute("loggedUser");

                for(UserLibro userLibro : userLibroRepository.findByUserId(loggedUser.getId())){
                        if(userLibro.getLibro().getId() == id) userLibroRepository.deleteById(userLibro.getId());
                }

                return "redirect:/iTuoiLibri";
        }
}
