package br.com.skip.the.dishes.command.resource

import org.springframework.restdocs.constraints.ConstraintDescriptions
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.snippet.Attributes.key
import org.springframework.util.StringUtils

class DocumentationConstrainedFields<T>(input: Class<T>) {

    private val constraintDescriptions = ConstraintDescriptions(input)

    fun withPath(path: String): FieldDescriptor {
        val value = StringUtils.collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path), ". ")

        return fieldWithPath(path).attributes(key("constraints").value(value))
    }

}
