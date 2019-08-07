package com.curiouslyodd.intricacies.capabilities.skills;

import java.util.concurrent.Callable;

public class SkillFactory implements Callable<ISkill> {
	@Override
	public ISkill call() throws Exception {
		return new Skill();
	}
}