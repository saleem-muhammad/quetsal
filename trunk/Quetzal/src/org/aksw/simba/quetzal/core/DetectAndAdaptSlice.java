package org.aksw.simba.quetzal.core;

import org.openrdf.OpenRDFException;
import org.openrdf.query.algebra.Slice;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.query.algebra.helpers.QueryModelVisitorBase;

public final class DetectAndAdaptSlice
extends QueryModelVisitorBase<OpenRDFException>
{
private final int limit;
private boolean detected = false;

DetectAndAdaptSlice(int limit)
{
    this.limit = limit;
    
}
@Override
public void meet(StatementPattern node) throws OpenRDFException {
       super.meet(node);
}
@Override
public void meet(Slice node)
    throws OpenRDFException
{
    if (limit < node.getLimit())
    	   node.setLimit(limit);
    this.detected = true;
    super.meet(node);
}

public boolean isDetected()
{
    return detected;
}
}
