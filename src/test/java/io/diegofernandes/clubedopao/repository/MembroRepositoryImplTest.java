package io.diegofernandes.clubedopao.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import io.diegofernandes.clubedopao.BaseTest;
import io.diegofernandes.clubedopao.model.Membro;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;

@DatabaseSetup("/dataset/membros.xml")
public class MembroRepositoryImplTest extends BaseTest {

	@Autowired
	private MembroRepository membroRepository;

	@Test
	public void testFind() {
		final Membro membro = this.membroRepository.find(1l);

		assertNotNull(membro);
		assertEquals(membro.getNome(), "Danny R. Dehaven");
		assertEquals(membro.getEmail(), "dannyrdehaven@rhyta.com");

	}

	@Test
	public void testFindAll() {
		final List<Membro> membros = this.membroRepository.findAll();

		assertEquals(membros.size(), 3);
	}

	@Test
	public void testFindAllIntegerInteger() {
		final List<Membro> membros = this.membroRepository.findAll(0, 1);

		assertEquals(membros.size(), 1);

	}

	@Test
	public void testSave() {
		Membro membro = new Membro(null, "Diego Fernandes", "diego@diego.com",
				Arrays.asList(1, 2));

		membro = this.membroRepository.save(membro);

		final Membro membro2 = this.membroRepository.find(membro.getId());

		assertNotNull(membro2);
		assertEquals(membro2.getNome(), membro.getNome());
		assertEquals(membro2.getEmail(), membro.getEmail());
		assertThat(membro2.getDisponibilidade(), is(membro.getDisponibilidade()));
	}

	@Test
	public void testRemove() {
		final Membro membro = this.membroRepository.find(1l);
		this.membroRepository.remove(membro);

		assertNull(this.membroRepository.find(membro.getId()));
	}
	
	@Test
	public void testRemoveById() {
		this.membroRepository.remove(1L);

		assertNull(this.membroRepository.find(1L));
	}

	@Test
	public void testFindByEmail() {
		final Membro membro = this.membroRepository
				.findByEmail("dannyrdehaven@rhyta.com");
		assertNotNull(membro);
		final Long id = 1L;
		assertEquals(membro.getId(), id);
		assertEquals(membro.getNome(), "Danny R. Dehaven");

	}
	
	@Test
	public void testFindByNameOrEmail(){
		final String filter = "danny";
		final List<Membro> membros = this.membroRepository.findByNameOrEmail(filter, null, null);
		final Pattern p = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
		Matcher matcherName = p.matcher("");
		Matcher matcherEmail = p.matcher("");
		for (Membro membro : membros) {
			matcherName.reset(membro.getNome());
			matcherEmail.reset(membro.getEmail());
			assertTrue(matcherName.find() || matcherEmail.find());
			
		}
	
	}

}
