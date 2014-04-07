package io.diegofernandes.clubedopao.repository;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import io.diegofernandes.clubedopao.BaseTest;
import io.diegofernandes.clubedopao.model.Membro;

import java.util.Arrays;
import java.util.List;

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
		assertThat(membro2.getDisponbilidade(), is(membro.getDisponbilidade()));
	}

	@Test
	public void testRemove() {
		final Membro membro = this.membroRepository.find(1l);
		this.membroRepository.remove(membro);

		assertNull(this.membroRepository.find(membro.getId()));
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

}
