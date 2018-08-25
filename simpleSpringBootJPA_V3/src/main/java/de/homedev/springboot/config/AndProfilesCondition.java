package de.homedev.springboot.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

public class AndProfilesCondition implements Condition {

	public static final String VALUE = "value";
	public static final String DEFAULT_PROFILE = "default";

	@Override
	public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
		if (context.getEnvironment() == null) {
			return true;
		}
		final MultiValueMap<String, Object> attrs = metadata.getAllAnnotationAttributes(Profile.class.getName());
		if (attrs == null) {
			return true;
		}
		final String[] activeProfiles = context.getEnvironment().getActiveProfiles();
		final String[] definedProfiles = (String[]) attrs.getFirst(VALUE);
		final Set<String> allowedProfiles = new HashSet<>(1);
		final Set<String> restrictedProfiles = new HashSet<>(1);
		for (final String nextDefinedProfile : definedProfiles) {
			if (!nextDefinedProfile.isEmpty() && nextDefinedProfile.charAt(0) == '!') {
				restrictedProfiles.add(nextDefinedProfile.substring(1, nextDefinedProfile.length()));
				continue;
			}
			allowedProfiles.add(nextDefinedProfile);
		}
		int activeAllowedCount = 0;
		for (final String nextActiveProfile : activeProfiles) {
			// quick exit when default profile is active and allowed profiles is
			// empty
			if (DEFAULT_PROFILE.equals(nextActiveProfile) && allowedProfiles.isEmpty()) {
				continue;
			}
			// quick exit when one of active profiles is restricted
			if (restrictedProfiles.contains(nextActiveProfile)) {
				return false;
			}
			// just go ahead when there is no allowed profiles (just need to
			// check that there is no active restricted profiles)
			if (allowedProfiles.isEmpty()) {
				continue;
			}
			if (allowedProfiles.contains(nextActiveProfile)) {
				activeAllowedCount++;
			}
		}
		return activeAllowedCount == allowedProfiles.size();
	}

}
