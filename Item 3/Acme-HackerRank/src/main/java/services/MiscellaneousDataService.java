package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curricula;
import domain.Hacker;
import domain.MiscellaneousData;

import repositories.MiscellaneousDataRepository;

@Service
@Transactional
public class MiscellaneousDataService {

	@Autowired
	private MiscellaneousDataRepository	miscellaneousDataRepository;
	
	@Autowired
	private HackerService				hackerService;
		
	@Autowired
	private CurriculaService curriculaService;


	//Metodos CRUD

	public MiscellaneousData create() {
		final MiscellaneousData mRecord = new MiscellaneousData();
		mRecord.setFreeText("");
		Collection<String> attachments = new ArrayList<String>();
		mRecord.setAttachments(attachments);
		return mRecord;
	}

	public Collection<MiscellaneousData> findAll() {
		final Collection<MiscellaneousData> res = this.miscellaneousDataRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public MiscellaneousData findOne(final int id) {
		Assert.isTrue(id != 0);
		final MiscellaneousData res = this.miscellaneousDataRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public MiscellaneousData save(final MiscellaneousData miscellaneousData) {
		final Hacker me = this.hackerService.findByPrincipal();
		Assert.notNull(me, "You must be logged in the system");
		Assert.notNull(miscellaneousData);
		final Curricula curricula = this.curriculaService.findCurriculaByHacker(me.getId());
		if (miscellaneousData.getId() != 0)
			Assert.isTrue(this.hackerService.findHackerByMiscellaneous(miscellaneousData.getId()) == me);
		
			
		final MiscellaneousData res = this.miscellaneousDataRepository.save(miscellaneousData);
		
		Assert.notNull(curricula.getMiscellaneous().contains(res));
		
		//TODO
		if (miscellaneousData.getId() == 0){
			Collection<MiscellaneousData> misc = curricula.getMiscellaneous();
			misc.add(miscellaneousData);
			curricula.setMiscellaneous(misc);
			this.curriculaService.save(curricula);
		}
		return res;
	}

	public void delete(final MiscellaneousData mR) {
		final Hacker me = this.hackerService.findByPrincipal();
		Assert.notNull(me, "You must be logged in the system");
		Assert.isTrue(this.hackerService.findHackerByMiscellaneous(mR.getId()) == me, "No puede borrar un MiscellaneousData que no pertenezca a su historia.");
		Assert.notNull(mR);
		Assert.isTrue(mR.getId() != 0);
		final MiscellaneousData res = this.findOne(mR.getId());
		final Curricula curricula = this.curriculaService.findCurriculaByHacker(me.getId());
		
		final Collection<MiscellaneousData> miscellaneousDatas = curricula.getMiscellaneous();
		Assert.isTrue(miscellaneousDatas.contains(res));
		miscellaneousDatas.remove(res);
		
		this.miscellaneousDataRepository.delete(res.getId());
		//TODO
		curricula.setMiscellaneous(miscellaneousDatas);
		this.curriculaService.save(curricula);

	}

}
