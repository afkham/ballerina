/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package io.ballerinalang.compiler.syntax.tree;

import io.ballerinalang.compiler.internal.parser.tree.STNode;

import java.util.Objects;
import java.util.Optional;

/**
 * This is a generated syntax tree node.
 *
 * @since 2.0.0
 */
public class QueryExpressionNode extends ExpressionNode {

    public QueryExpressionNode(STNode internalNode, int position, NonTerminalNode parent) {
        super(internalNode, position, parent);
    }

    public QueryConstructTypeNode queryConstructType() {
        return childInBucket(0);
    }

    public QueryPipelineNode queryPipeline() {
        return childInBucket(1);
    }

    public SelectClauseNode selectClause() {
        return childInBucket(2);
    }

    public Optional<OnConflictClauseNode> onConflictClause() {
        return optionalChildInBucket(3);
    }

    public Optional<LimitClauseNode> limitClause() {
        return optionalChildInBucket(4);
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T apply(NodeTransformer<T> visitor) {
        return visitor.transform(this);
    }

    @Override
    protected String[] childNames() {
        return new String[]{
                "queryConstructType",
                "queryPipeline",
                "selectClause",
                "onConflictClause",
                "limitClause"};
    }

    public QueryExpressionNode modify(
            QueryConstructTypeNode queryConstructType,
            QueryPipelineNode queryPipeline,
            SelectClauseNode selectClause,
            OnConflictClauseNode onConflictClause,
            LimitClauseNode limitClause) {
        if (checkForReferenceEquality(
                queryConstructType,
                queryPipeline,
                selectClause,
                onConflictClause,
                limitClause)) {
            return this;
        }

        return NodeFactory.createQueryExpressionNode(
                queryConstructType,
                queryPipeline,
                selectClause,
                onConflictClause,
                limitClause);
    }

    public QueryExpressionNodeModifier modify() {
        return new QueryExpressionNodeModifier(this);
    }

    /**
     * This is a generated tree node modifier utility.
     *
     * @since 2.0.0
     */
    public static class QueryExpressionNodeModifier {
        private final QueryExpressionNode oldNode;
        private QueryConstructTypeNode queryConstructType;
        private QueryPipelineNode queryPipeline;
        private SelectClauseNode selectClause;
        private OnConflictClauseNode onConflictClause;
        private LimitClauseNode limitClause;

        public QueryExpressionNodeModifier(QueryExpressionNode oldNode) {
            this.oldNode = oldNode;
            this.queryConstructType = oldNode.queryConstructType();
            this.queryPipeline = oldNode.queryPipeline();
            this.selectClause = oldNode.selectClause();
            this.onConflictClause = oldNode.onConflictClause().orElse(null);
            this.limitClause = oldNode.limitClause().orElse(null);
        }

        public QueryExpressionNodeModifier withQueryConstructType(
                QueryConstructTypeNode queryConstructType) {
            Objects.requireNonNull(queryConstructType, "queryConstructType must not be null");
            this.queryConstructType = queryConstructType;
            return this;
        }

        public QueryExpressionNodeModifier withQueryPipeline(
                QueryPipelineNode queryPipeline) {
            Objects.requireNonNull(queryPipeline, "queryPipeline must not be null");
            this.queryPipeline = queryPipeline;
            return this;
        }

        public QueryExpressionNodeModifier withSelectClause(
                SelectClauseNode selectClause) {
            Objects.requireNonNull(selectClause, "selectClause must not be null");
            this.selectClause = selectClause;
            return this;
        }

        public QueryExpressionNodeModifier withOnConflictClause(
                OnConflictClauseNode onConflictClause) {
            Objects.requireNonNull(onConflictClause, "onConflictClause must not be null");
            this.onConflictClause = onConflictClause;
            return this;
        }

        public QueryExpressionNodeModifier withLimitClause(
                LimitClauseNode limitClause) {
            Objects.requireNonNull(limitClause, "limitClause must not be null");
            this.limitClause = limitClause;
            return this;
        }

        public QueryExpressionNode apply() {
            return oldNode.modify(
                    queryConstructType,
                    queryPipeline,
                    selectClause,
                    onConflictClause,
                    limitClause);
        }
    }
}
