package br.com.usinasantafe.pcb.model.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.pcb.model.bean.estaticas.BagBean;
import br.com.usinasantafe.pcb.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCargaBean;
import br.com.usinasantafe.pcb.model.bean.estaticas.SafraBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.CabecCargaBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.CabecTransfBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.ItemCargaBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.ItemTransfBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pcb.model.dao.LogErroDAO;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "pcb_db";
	public static final int FORCA_BD_VERSION = 4;

	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {

		super(context, FORCA_DB_NAME, null, FORCA_BD_VERSION);;

		instance = this;
		
	}

	@Override
	public void close() {
		super.close();
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		
		try{

			createAllInitialV2_3_4(cs);

		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
                          ConnectionSource cs,
                          int oldVersion,
                          int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){

				dropAllInitialV1(cs);
				createAllInitialV2_3_4(cs);

			}
			else if(oldVersion == 2 && newVersion == 3){

				//Versão 1.03
				dropAllInitialV2_3_4(cs);
				createAllInitialV2_3_4(cs);

			}
			else if(oldVersion == 3 && newVersion == 4){

				//Versão 1.04
				dropAllInitialV2_3_4(cs);
				createAllInitialV2_3_4(cs);

			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

	public void dropAllInitialV1(ConnectionSource cs){

		try {

			TableUtils.dropTable(cs, BagBean.class, true);
			TableUtils.dropTable(cs, FuncBean.class, true);
			TableUtils.dropTable(cs, OrdemCargaBean.class, true);
			TableUtils.dropTable(cs, CabecCargaBean.class, true);
			TableUtils.dropTable(cs, ConfigBean.class, true);
			TableUtils.dropTable(cs, ItemCargaBean.class, true);
			TableUtils.dropTable(cs, LogErroBean.class, true);
			TableUtils.dropTable(cs, LogProcessoBean.class, true);

		} catch (Exception e) {
			LogErroDAO.getInstance().insertLogErro(e);
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}

	}

	public void createAllInitialV2_3_4(ConnectionSource cs){

		try {

			TableUtils.createTable(cs, BagBean.class);
			TableUtils.createTable(cs, FuncBean.class);
			TableUtils.createTable(cs, OrdemCargaBean.class);
			TableUtils.createTable(cs, SafraBean.class);
			TableUtils.createTable(cs, CabecCargaBean.class);
			TableUtils.createTable(cs, CabecTransfBean.class);
			TableUtils.createTable(cs, ConfigBean.class);
			TableUtils.createTable(cs, ItemCargaBean.class);
			TableUtils.createTable(cs, ItemTransfBean.class);
			TableUtils.createTable(cs, LogErroBean.class);
			TableUtils.createTable(cs, LogProcessoBean.class);

		} catch (Exception e) {
			LogErroDAO.getInstance().insertLogErro(e);
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
	}

	public void dropAllInitialV2_3_4(ConnectionSource cs){

		try {

			TableUtils.dropTable(cs, BagBean.class, true);
			TableUtils.dropTable(cs, FuncBean.class, true);
			TableUtils.dropTable(cs, OrdemCargaBean.class, true);
			TableUtils.dropTable(cs, SafraBean.class, true);
			TableUtils.dropTable(cs, CabecCargaBean.class, true);
			TableUtils.dropTable(cs, CabecTransfBean.class, true);
			TableUtils.dropTable(cs, ConfigBean.class, true);
			TableUtils.dropTable(cs, ItemCargaBean.class, true);
			TableUtils.dropTable(cs, ItemTransfBean.class, true);
			TableUtils.dropTable(cs, LogErroBean.class, true);
			TableUtils.dropTable(cs, LogProcessoBean.class, true);

		} catch (Exception e) {
			LogErroDAO.getInstance().insertLogErro(e);
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}

	}

}
