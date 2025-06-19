package com.puriihuaman.literalura.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import com.puriihuaman.literalura.service.BooksService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the title value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = BooksTitleUnique.BooksTitleUniqueValidator.class
)
public @interface BooksTitleUnique {

    String message() default "{Exists.books.title}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class BooksTitleUniqueValidator implements ConstraintValidator<BooksTitleUnique, String> {

        private final BooksService booksService;
        private final HttpServletRequest request;

        public BooksTitleUniqueValidator(final BooksService booksService,
                final HttpServletRequest request) {
            this.booksService = booksService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(booksService.get(UUID.fromString(currentId)).getTitle())) {
                // value hasn't changed
                return true;
            }
            return !booksService.titleExists(value);
        }

    }

}
