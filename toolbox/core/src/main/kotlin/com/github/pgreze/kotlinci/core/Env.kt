package com.github.pgreze.kotlinci.core

fun nonEmptyEnv(key: String): String? =
    System.getenv(key).takeUnless(String::isNullOrEmpty)

fun requireEnv(key: String): String =
    System.getenv(key).takeUnless(String::isNullOrEmpty)
        ?: throw NullPointerException("$key not found or empty")

fun requireGithubToken(): String =
    requireEnv("GITHUB_API_TOKEN")

fun requireSlackToken(): String =
    requireEnv("SLACK_TOKEN")
