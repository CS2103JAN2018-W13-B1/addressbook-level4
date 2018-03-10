package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalTags.BUDDIES;
import static seedu.address.testutil.TypicalTags.FRIENDS;
import static seedu.address.testutil.TypicalTags.OWES_MONEY;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;


public class TagsContainKeywordsPredicateTest {

    @Test
    public void equals() {
        Set<Tag> firstPredicateKeywordList = new HashSet<>(Arrays.asList(FRIENDS, BUDDIES));
        Set<Tag> secondPredicateKeywordList = new HashSet<>(Collections.singletonList(FRIENDS));

        TagsContainKeywordsPredicate firstPredicate = new TagsContainKeywordsPredicate(firstPredicateKeywordList);
        TagsContainKeywordsPredicate secondPredicate = new TagsContainKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainKeywordsPredicate firstPredicateCopy = new TagsContainKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // One keyword
        TagsContainKeywordsPredicate predicate =
                new TagsContainKeywordsPredicate(new HashSet<>(Collections.singletonList(OWES_MONEY)));
        assertTrue(predicate.test(new PersonBuilder().withTags(OWES_MONEY.tagName).build()));

        // Multiple keywords
        predicate = new TagsContainKeywordsPredicate(new HashSet<>(Arrays.asList(OWES_MONEY, BUDDIES)));
        assertTrue(predicate.test(new PersonBuilder().withTags(OWES_MONEY.tagName, BUDDIES.tagName).build()));

        // Only one matching keyword
        predicate = new TagsContainKeywordsPredicate(new HashSet<>(Arrays.asList(FRIENDS, BUDDIES)));
        assertTrue(predicate.test(new PersonBuilder().withTags(OWES_MONEY.tagName, BUDDIES.tagName).build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainKeywordsPredicate predicate =
                new TagsContainKeywordsPredicate(new HashSet<>(Collections.emptyList()));
        assertFalse(predicate.test(new PersonBuilder().withTags(OWES_MONEY.tagName).build()));

        // Non-matching keyword
        predicate = new TagsContainKeywordsPredicate(new HashSet<>(Collections.singletonList(OWES_MONEY)));
        assertFalse(predicate.test(new PersonBuilder().withTags(BUDDIES.tagName, FRIENDS.tagName).build()));
    }
}
