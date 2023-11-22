package br.com.android.victorcs.mvvmarvel.domain.mapper

interface DomainMapper<in T, out Dto> {
    fun toDomain(from: T): Dto
    fun toDomain(from: List<T>): List<Dto>
}
