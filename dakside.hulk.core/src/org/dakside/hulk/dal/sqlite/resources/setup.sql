CREATE TABLE IF NOT EXISTS meta (
    title TEXT,
    author TEXT,
    desc TEXT,
    version INTEGER
);

INSERT INTO meta VALUES (
    'Hulk Project Untitled 01',
    'Author Name',
    'HULK project description',
    1
);

CREATE TABLE IF NOT EXISTS variety (
    varietyID INTEGER PRIMARY KEY,
    langName TEXT UNIQUE,
    langCode TEXT UNIQUE,
    desc TEXT
);

CREATE TABLE IF NOT EXISTS folder (
    folderID INTEGER PRIMARY KEY,
    name UNIQUE
);

CREATE TABLE IF NOT EXISTS folder_varieties (
    folderID,
    varietyID,
    PRIMARY KEY (folderID, varietyID),
    FOREIGN KEY(varietyID) REFERENCES variety(varietyID),
    FOREIGN KEY(folderID) REFERENCES folder(folderID)
);

CREATE TABLE IF NOT EXISTS word (
    wordID INTEGER PRIMARY KEY,
    ipa TEXT,
    gloss TEXT,
    comment TEXT
);

CREATE TABLE IF NOT EXISTS cognate (
    cognateID INTEGER PRIMARY KEY,
    wordID INTEGER,
    finalForm TEXT,
    FOREIGN KEY(wordID) REFERENCES word(wordID)
);

CREATE TABLE IF NOT EXISTS cognateset (
    cognatesetID INTEGER PRIMARY KEY,
    label TEXT
);

CREATE TABLE IF NOT EXISTS cognate_cognateset (
    cognateID,
    cognatesetID,
    PRIMARY KEY (cognateID, cognatesetID),
    FOREIGN KEY(cognateID) REFERENCES cognate(cognateID),
    FOREIGN KEY(cognatesetID) REFERENCES cognateset(cognatesetID)
);

CREATE INDEX IF NOT EXISTS variety_langcode ON variety (langCode);
CREATE INDEX IF NOT EXISTS variety_langName ON variety (langName);