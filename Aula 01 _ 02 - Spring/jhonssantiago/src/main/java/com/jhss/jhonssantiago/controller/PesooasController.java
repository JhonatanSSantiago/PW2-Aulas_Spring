/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhss.jhonssantiago.controller;

import com.jhss.jhonssantiago.dao.PessoaDAO;
import com.jhss.jhonssantiago.model.Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jhons
 */
@Controller
@RequestMapping("pessoas")
public class PesooasController {

    PessoaDAO dao = new PessoaDAO();

    
    @ResponseBody
    @GetMapping("/exemplo")
    public String exemplo() {
        return "Controller de Pessoas!";
    }

    /**
     * @param pessoa necessário devido utilizar no form.html o th:object que faz
     * referência ao objeto esperado no controller.
     * @return
     */
    @GetMapping("/form")
    public String form(Pessoa pessoa) {
        return "/pessoas/form";
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("pessoas", dao.buscarPessoas());
        return new ModelAndView("/pessoas/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Pessoa pessoa) {
        dao.save(pessoa);
        return new ModelAndView("redirect:/pessoas/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada
     * diretamente na URL
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        dao.remove(id);
        return new ModelAndView("redirect:/pessoas/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada
     * diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", dao.buscarPessoa(id));
        return new ModelAndView("/pessoas/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Pessoa pessoa) {
        dao.update(pessoa);
        return new ModelAndView("redirect:/pessoas/list");
    }

}

