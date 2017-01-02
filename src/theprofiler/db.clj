(ns theprofiler.db
  (:require [clj-postgresql.core :as pg]
            [clojure.java.jdbc :as jdbc]))

(def db2 (pg/pool :host "localhost:5432"
                  :user "theprofiler"
                  :dbname "theprofiler"
                  :password "admin2016"))

(defn login-f [user]
	(jdbc/query db2 [(str "select * from users where username = '" user "'")]))

(defn searchf [prof]
	(jdbc/query db2 [(str "select * from profiles where name like '%" prof "%'")]))

(defn searchid [id]
	(jdbc/query db2 [(str "select * from profiles where uuid = '" id "'")]))

(defn admin? [username]
	(= 1 (:admin (first (jdbc/query db2 [(str "select admin from users where username = '" username "'")])))))

;UPDATE 
(defn addprofdb [nm age address cas job org photos profilephoto uuid]
	(jdbc/insert! db2 :profiles 
		{:name nm :age age :address address :kasus cas :job job :organisation org :photos photos :profilephoto profilephoto :uuid uuid}))

