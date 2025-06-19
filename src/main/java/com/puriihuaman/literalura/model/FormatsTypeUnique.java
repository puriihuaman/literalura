package com.puriihuaman.literalura.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import com.puriihuaman.literalura.service.FormatsService;
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
 * Validate that the type value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = FormatsTypeUnique.FormatsTypeUniqueValidator.class
)
public @interface FormatsTypeUnique {

    String message() default "{Exists.formats.type}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class FormatsTypeUniqueValidator implements ConstraintValidator<FormatsTypeUnique, String> {

        private final FormatsService formatsService;
        private final HttpServletRequest request;

        public FormatsTypeUniqueValidator(final FormatsService formatsService,
                final HttpServletRequest request) {
            this.formatsService = formatsService;
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
            if (currentId != null && value.equalsIgnoreCase(formatsService.get(UUID.fromString(currentId)).getType())) {
                // value hasn't changed
                return true;
            }
            return !formatsService.typeExists(value);
        }

    }

}
