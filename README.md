# Emoji Java
[![Version](https://img.shields.io/maven-central/v/com.cameronprater/emoji-java?logo=apache-maven&style=for-the-badge)](https://search.maven.org/artifact/com.cameronprater/emoji-java)
[![Build](<https://img.shields.io/github/workflow/status/cameronprater/emoji-java?logo=github&style=for-the-badge>)](https://github.com/cameronprater/emoji-java/actions)
[![License](https://img.shields.io/github/license/cameronprater/emoji-java?logo=apache&style=for-the-badge)](/LICENSE)

This is a fork of the Emoji Java project including emojis introduced up to Unicode 13.0. Modified to optimally integrate
with Java libraries for the Discord API, this fork removes HTML utilities, unnecessary emoji metadata, and uses Jackson 
instead of JSON-Java.

## Installation
### Maven
```
<dependencies>
    <dependency>
        <groupId>com.cameronprater</groupId>
        <artifactId>emoji-core</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

### Gradle
```
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.cameronprater:emoji-core:1.0-SNAPSHOT")
}
```

## Emojis
All the emojis recognized by this library can be found [here](EMOJIS.md).

## Usage
Information on how to use the library can be found at the [original repository](https://github.com/vdurmont/emoji-java).