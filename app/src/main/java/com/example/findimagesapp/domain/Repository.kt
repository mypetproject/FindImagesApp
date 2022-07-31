package com.example.findimagesapp.domain

/**
 * Repository for working with a search service
 *
 * @author S. Kishkar
 */
interface Repository {

    /**
     * Submits the first search request
     *
     * @param query search key
     */
    fun downloadDataFirstTime(query: String)

    /**
     * Submits the non-first search request
     *
     */
    fun downloadAdditionalData()
}