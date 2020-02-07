package com.movilbox.modules.main.ui;

import com.movilbox.services.Post;
import com.movilbox.services.entities.Address;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {Post.class, Address.class})
class SimpleRealmModule {
}
