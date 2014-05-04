package io.diegofernandes.clubedopao.controller;

import io.diegofernandes.clubedopao.model.ListaEncarregadoPao;
import io.diegofernandes.clubedopao.model.Membro;
import io.diegofernandes.clubedopao.repository.MembroRepository;
import io.diegofernandes.clubedopao.service.MembroService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("membro")
public class MembroController {
	
	@Autowired
	private MembroRepository membroRepository;
	
	@Autowired
	private MembroService membroService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Membro find(@PathVariable Long id) {
		return membroRepository.find(id);
	}

	@RequestMapping(value="/all",method=RequestMethod.GET)
	public @ResponseBody List<Membro> findAll(Integer firstResult, Integer maxResults) {
		return membroRepository.findAll(firstResult, maxResults);
	}
	
	@RequestMapping(value = "/filter/{filter}", method= RequestMethod.GET)
	public List<Membro> findByNameOrEmail(String filter, Integer firstResult,
			Integer maxResults) {
		return membroRepository.findByNameOrEmail(filter, firstResult,
				maxResults);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody void save(@RequestBody Membro membro) {
		  membroRepository.save(membro);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody void remove(@PathVariable Long id) {
		membroRepository.remove(id);
	}

	@RequestMapping(value="/gerarListaEncarregadosPao/{data}", method=RequestMethod.GET)
	public @ResponseBody ListaEncarregadoPao gerarListaEncarregadosPao(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date data) {
		return membroService.gerarListaEncarregadosPao(data);
	}
	
}
