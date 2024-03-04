package com.leaf.domain.mapper

interface Mapper<Domain, RepositoryModel> {

    fun mapFromModel(type: RepositoryModel?): Domain?

    fun mapToModel(type: Domain?): RepositoryModel?
}