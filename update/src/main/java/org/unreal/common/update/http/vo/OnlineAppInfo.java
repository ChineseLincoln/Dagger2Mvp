package org.unreal.common.update.http.vo;

/**
 * <b>类名称：</b> OnlineAppInfo <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年04月25日 上午10:03<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class OnlineAppInfo {

    /**
     * name : 96166
     * version : 1
     * changelog : First release
     * updated_at : 1461547998
     * versionShort : 1.0
     * build : 1
     * installUrl : http://download.fir.im/v2/app/install/571d739de75e2d1f1000001b?download_token=1a1cdd446b662c1d83e93c33ba8271e7
     * install_url : http://download.fir.im/v2/app/install/571d739de75e2d1f1000001b?download_token=1a1cdd446b662c1d83e93c33ba8271e7
     * direct_install_url : http://download.fir.im/v2/app/install/571d739de75e2d1f1000001b?download_token=1a1cdd446b662c1d83e93c33ba8271e7
     * update_url : http://fir.im/k7am
     * binary : {"fsize":9459538}
     * <p/>
     * installUrl	String	安装地址（兼容旧版字段）
     * install_url	String	安装地址(新增字段)
     * update_url	String	更新地址(新增字段)
     */

    private String name;
    private int version;
    private String changelog;
    private int updated_at;
    private String versionShort;
    private String build;
    private String installUrl;
    private String install_url;
    private String direct_install_url;
    private String update_url;
    /**
     * fsize : 9459538
     */

    private BinaryEntity binary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getVersionShort() {
        return versionShort;
    }

    public void setVersionShort(String versionShort) {
        this.versionShort = versionShort;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getInstallUrl() {
        return installUrl;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    public String getInstall_url() {
        return install_url;
    }

    public void setInstall_url(String install_url) {
        this.install_url = install_url;
    }

    public String getDirect_install_url() {
        return direct_install_url;
    }

    public void setDirect_install_url(String direct_install_url) {
        this.direct_install_url = direct_install_url;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }

    public BinaryEntity getBinary() {
        return binary;
    }

    public void setBinary(BinaryEntity binary) {
        this.binary = binary;
    }

    public static class BinaryEntity {
        private int fsize;

        public int getFsize() {
            return fsize;
        }

        public void setFsize(int fsize) {
            this.fsize = fsize;
        }
    }
}
