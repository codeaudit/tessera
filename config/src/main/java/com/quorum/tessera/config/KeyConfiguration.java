package com.quorum.tessera.config;

import com.quorum.tessera.config.adapters.PathAdapter;
import com.quorum.tessera.config.constraints.ValidKeyData;
import com.quorum.tessera.config.constraints.ValidPath;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.nio.file.Path;
import java.util.List;
import javax.validation.Valid;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(factoryMethod = "create")
public class KeyConfiguration extends ConfigItem {

    @ValidPath(checkExists = true, message = "Password file does not exist")
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(PathAdapter.class)
    private final Path passwordFile;

    private final List<String> passwords;

    @Valid
    @ValidKeyData
    @NotNull
    @Size(min = 1, message = "At least 1 public/private key pair must be provided")
    private final List<KeyData> keyData;

    public KeyConfiguration(final Path passwordFile, final List<String> passwords, final List<KeyData> keyData) {
        this.passwordFile = passwordFile;
        this.passwords = passwords;
        this.keyData = keyData;
    }

    private static KeyConfiguration create() {
        return new KeyConfiguration(null, null, null);
    }

    public Path getPasswordFile() {
        return this.passwordFile;
    }

    public List<String> getPasswords() {
        return this.passwords;
    }

    public List<KeyData> getKeyData() {
        return this.keyData;
    }

}
