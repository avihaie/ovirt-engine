package org.ovirt.engine.ui.webadmin.section.main.presenter.tab.storage;

import org.ovirt.engine.core.common.businessentities.StorageDomain;
import org.ovirt.engine.core.common.businessentities.VmTemplate;
import org.ovirt.engine.ui.common.place.PlaceRequestFactory;
import org.ovirt.engine.ui.common.presenter.AbstractSubTabPresenter;
import org.ovirt.engine.ui.common.uicommon.model.SearchableDetailModelProvider;
import org.ovirt.engine.ui.common.widget.tab.ModelBoundTabData;
import org.ovirt.engine.ui.uicommonweb.models.storage.StorageListModel;
import org.ovirt.engine.ui.uicommonweb.models.storage.StorageRegisterTemplateListModel;
import org.ovirt.engine.ui.webadmin.ApplicationConstants;
import org.ovirt.engine.ui.uicommonweb.place.WebAdminApplicationPlaces;
import org.ovirt.engine.ui.webadmin.section.main.presenter.tab.StorageSelectionChangeEvent;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TabContentProxyPlace;

public class SubTabStorageRegisterTemplatePresenter extends AbstractSubTabPresenter<StorageDomain, StorageListModel, StorageRegisterTemplateListModel, SubTabStorageRegisterTemplatePresenter.ViewDef, SubTabStorageRegisterTemplatePresenter.ProxyDef> {

    @ProxyCodeSplit
    @NameToken(WebAdminApplicationPlaces.storageTemplateRegisterSubTabPlace)
    public interface ProxyDef extends TabContentProxyPlace<SubTabStorageRegisterTemplatePresenter> {
    }

    public interface ViewDef extends AbstractSubTabPresenter.ViewDef<StorageDomain> {
    }

    @TabInfo(container = StorageSubTabPanelPresenter.class)
    static TabData getTabData(ApplicationConstants applicationConstants,
            SearchableDetailModelProvider<VmTemplate, StorageListModel, StorageRegisterTemplateListModel> modelProvider) {
        return new ModelBoundTabData(applicationConstants.storageTemplateBackupSubTabLabel(), 3, modelProvider);
    }

    @Inject
    public SubTabStorageRegisterTemplatePresenter(EventBus eventBus, ViewDef view, ProxyDef proxy,
                                                  PlaceManager placeManager,
                                                  SearchableDetailModelProvider<VmTemplate, StorageListModel, StorageRegisterTemplateListModel> modelProvider) {
        super(eventBus, view, proxy, placeManager, modelProvider,
                StorageSubTabPanelPresenter.TYPE_SetTabContent);
    }

    @Override
    protected PlaceRequest getMainTabRequest() {
        return PlaceRequestFactory.get(WebAdminApplicationPlaces.storageMainTabPlace);
    }

    @ProxyEvent
    public void onStorageSelectionChange(StorageSelectionChangeEvent event) {
        updateMainTabSelection(event.getSelectedItems());
    }

}
