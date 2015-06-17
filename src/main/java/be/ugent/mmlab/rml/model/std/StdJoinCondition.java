/* 
 * Copyright 2011 Antidot opensource@antidot.net
 * https://github.com/antidot/db2triples
 * 
 * DB2Triples is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as 
 * published by the Free Software Foundation; either version 2 of 
 * the License, or (at your option) any later version.
 * 
 * DB2Triples is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/***************************************************************************
 *
 * R2RML Model : Standard Join Condition Class
 *
 * A join condition is a resource that has 
 * exactly two properties: 
 * 	- rr:child, whose value is known as the
 * 	  join condition's child column
 * 	- rr:parent, whose value is known as the
 * 	  join condition's parent column 
 * 
 ****************************************************************************/
package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.JoinCondition;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StdJoinCondition implements JoinCondition {

    // Log
    private static final Logger log = LogManager.getLogger(StdJoinCondition.class);
    private String child;
    private String parent;

    /**
     *
     * @param child
     * @param parent
     */
    public StdJoinCondition(String child, String parent) {
        setChild(child);
        setParent(parent);
    }

    private void setParent(String parent) {
        if (parent == null) {
            log.error("Invalid Structure "
                    + "[StdJoinCondition:setParent] A join condition must "
                    + "have a parent column name.");
        }
        // old code
//		if (!SQLDataValidator.isValidSQLIdentifier(parent))
//			throw new InvalidR2RMLSyntaxException(
//					"[StdJoinCondition:setParent] Not a valid column "
//							+ "value : " + parent);

        // TODO check if reference is valid

        this.parent = parent;
    }

    private void setChild(String child) {
        if (child == null) {
            log.error("Invalid Structure "
                    + "[StdJoinCondition:construct] A join condition must "
                    + "have a child column name.");
        }
        // old code
//		if (!SQLDataValidator.isValidSQLIdentifier(child))
//			throw new InvalidR2RMLSyntaxException(
//					"[StdJoinCondition:setParent] Not a valid column "
//							+ "value : " + child);
        // TODO check if reference is valid


        this.child = child;

    }

    @Override
    public String getChild() {
		return child;
	}

    @Override
    public String getParent() {
        return parent;
    }

}
