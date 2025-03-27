package com.github.mikybars.challenge;

import static com.tngtech.archunit.library.GeneralCodingRules.DEPRECATED_API_SHOULD_NOT_BE_USED;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
import static com.tngtech.archunit.library.GeneralCodingRules.OLD_DATE_AND_TIME_CLASSES_SHOULD_NOT_BE_USED;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.github.mikybars.challenge", importOptions = DoNotIncludeTests.class)
class BestPracticesTest {

  @ArchTest
  ArchRule noFieldInjection  = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

  @ArchTest
  ArchRule noGenericExceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

  @ArchTest
  ArchRule deprecatedApiShouldNotBeUsed = DEPRECATED_API_SHOULD_NOT_BE_USED;

  @ArchTest
  ArchRule noOldJavaApis = OLD_DATE_AND_TIME_CLASSES_SHOULD_NOT_BE_USED;
}
